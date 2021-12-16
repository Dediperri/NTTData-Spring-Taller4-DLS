package com.nttdata.spring.controllers;

import org.springframework.ui.Model;

import com.nttdata.spring.persistence.NTTDataCustomer;

/**
 * Interface asociada al controlador del cliente
 * 
 * @author Diego Lopez Strickland
 *
 */
public interface NTTDataCustomerControllerI {

	/**
	 * Recibe la peticion y muestra los clientes por la pantalla principal
	 * 
	 * @return String
	 * @param
	 */
	public String showClients(Model allClientsModel);

	/**
	 * Recibe la peticion e inserta un nuevo cliente
	 * 
	 * @return String
	 */
	public String addNewClient(NTTDataCustomer insertClient);

	/**
	 * Recibe la peticion y elimina un cliente
	 * 
	 * @return String
	 * @param
	 */
	public String removeClient(Long id);

	/**
	 * Recibe la peticion y muestra los clientes con un nombre especifico por la
	 * pantalla principal
	 * 
	 * @return String
	 * @param
	 */
	public String getClientsByName(String name, Model model);

	/**
	 * Recibe la peticion y muestra la pagina principal
	 * 
	 * @return String
	 */
	public String welcomePage();
	
	/**
	 * Recibe la peticion y muestra la pagina para insertar clientes
	 * 
	 * @return String
	 * @param
	 */
	public String addClientPage(Model client);
	
	/**
	 * Recibe la peticion y muestra la pagina buscar clientes por sus nombres
	 * 
	 * @return String
	 * @param
	 */
	public String getClientsByNamePage(Model client);
}
