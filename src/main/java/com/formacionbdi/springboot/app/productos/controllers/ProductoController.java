package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	@Autowired
	private Environment env;
	@Value("${server.port}")
	private  Integer port;
	@Autowired
	private IProductoService productoService;
	@GetMapping(value = "/listar")
	public List<Producto> listar(){	
		return productoService.findAll().stream().map(j->{
			j.setPort(Integer.valueOf(env.getProperty("local.server.port")));
			return j;
		}).collect(Collectors.toList());
	}
	@GetMapping(value = "/ver/{id}")
	public Producto detalle(@PathVariable("id") Long id) {
		Producto producto =  productoService.findById(id);
		//producto.setPort(Integer.valueOf(env.getProperty("local.server.port")));
		producto.setPort(port);
		/*boolean ok = false;
		if(ok == false)
			throw new Exception("No se pudo cargar el producto");*/
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return producto;
	}
	@PostMapping("/crear")
	public Producto  crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	@PutMapping("/editarProducto/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb =  productoService.findById(id);
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return productoService.save(productoDb);
	}
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
