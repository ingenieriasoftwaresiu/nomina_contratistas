/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAOImpl;

import Conexion.GestionSQL;
import DAO.ArchivoPlanoContratoDAO;
import DTO.ArchivoPlanoContrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author jorge.correa
 */
public class ArchivoPlanoContratoDAOImpl implements ArchivoPlanoContratoDAO{
    
    private static final String OBTENER_TODOS = "SELECT * FROM nomina.tbl_contratos ORDER BY txtConsecutivo";

    @Override
    public List<ArchivoPlanoContrato> obtenerTodos(){
        Vector arrContratos = null;
        String[] strTemp = null;
        List<ArchivoPlanoContrato> contratos = null;
        ArchivoPlanoContrato contrato = null;               
            
        arrContratos = GestionSQL.consultaSQL(OBTENER_TODOS, "Nomina", "18");

        if (arrContratos != null){
            contratos = new ArrayList<ArchivoPlanoContrato>();
            
            for(int i=0;i<arrContratos.size();i++){
                strTemp = arrContratos.get(i).toString().split(">");
                
                contrato = new ArchivoPlanoContrato();
                contrato.setConsecutivo(strTemp[0]);
                contrato.setContratista(strTemp[1]);
                contrato.setInterventor(strTemp[2]);
                contrato.setTipoContrato(strTemp[3]);
                contrato.setGrupo(strTemp[4]);
                contrato.setFechaInicio(strTemp[5]);
                contrato.setFechaFin(strTemp[6]);
                contrato.setDuracion(strTemp[7]);
                contrato.setValor(strTemp[8]);
                contrato.setCentroCostos(strTemp[9]);
                contrato.setConsecutivoLaborales(strTemp[10]);
                contrato.setNumeroCDP(strTemp[11]);
                contrato.setObjeto(strTemp[12]);
                contrato.setEstadoActual(strTemp[13]);
                contrato.setFechaCreacion(strTemp[14]);                       

                contratos.add(contrato);
            }
        }                   
        
        return contratos;
    }
    
}
