/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.dao;

import domain.PDV;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface PdvDao {

    List<PDV> getAll() throws Exception;
}
