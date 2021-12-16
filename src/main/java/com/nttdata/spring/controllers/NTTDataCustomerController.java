package com.nttdata.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.spring.persistence.NTTDataCustomer;
import com.nttdata.spring.services.NTTDataCustomerManagementServiceI;

/**
 * Implementacion de la interfaz del controllador de clientes
 * 
 * @author Diego Lopez Strickland
 *
 */
@Controller
@RequestMapping("/home/")
public class NTTDataCustomerController implements NTTDataCustomerControllerI {

	/** Servicio de gestion de clientes */
	@Autowired
	private NTTDataCustomerManagementServiceI customerService;

	@Override
	@GetMapping("/showClients")
	public String showClients(Model allClientsModel) {

		// Invocamos el servicio de clientes y devolvemos una lista con todos los
		// clientes
		List<NTTDataCustomer> showAllClients = new ArrayList<NTTDataCustomer>();
		showAllClients = customerService.searchAll();

		// Se retorna la lista de clientes a la vista y modelo
		allClientsModel.addAttribute("clientes", showAllClients);

		// Se muestra la pagina mostrarClientes.html
		return "mostrarClientes";
	}

	@Override
	@GetMapping("*")
	public String welcomePage() {
		// Se muestra la pagina home.html
		return "home";
	}

	@Override
	@GetMapping("/addClient")
	public String addClientPage(Model client) {
		// Se retorna el cliente al modelo
		client.addAttribute("insertClient", new NTTDataCustomer());
		// Se muestra la pagina insertarCliente.html
		return "insertarCliente";
	}

	@GetMapping("/getClientsByName")
	public String getClientsByNamePage(Model client) {
		// Creo el nombre vacio del cliente
		client.addAttribute("findClients", new String());
		// Se muestra la pagina buscarClientesNombre.html
		return "buscarClientesNombre";
	}

	@Override
	@PostMapping("/addClient/add")
	public String addNewClient(@ModelAttribute("insertClient") NTTDataCustomer insertClient) {
		// Inserto el cliente en la base de datos
		customerService.InsertOrUpdateCustomer(insertClient);
		// Se muestra la pagina home.html
		return "home";
	}

	@Override
	@RequestMapping(value = "/getClientsByName/find", params = "name")
	public String getClientsByName(@RequestParam String name, Model model) {
		// Invocamos el servicio de clientes y devolvemos una lista con todos los
		// clientes
		List<NTTDataCustomer> showAllClients = new ArrayList<NTTDataCustomer>();
		showAllClients = customerService.findByCustomerName(name);

		// Se retorna la lista de clientes a la vista y modelo
		model.addAttribute("clientes", showAllClients);

		// Mostramos la pagina mostrarClientesNombre.html
		return "mostrarClientesNombre";
	}

	@Override
	@RequestMapping(value = "/removeClient", params = "id")
	public String removeClient(@RequestParam Long id) {

		// Elimino el cliente de la base de datos
		customerService.DeleteCustomerById(id);
		// Se muestra la pagina home.html
		return "home";
	}

}
