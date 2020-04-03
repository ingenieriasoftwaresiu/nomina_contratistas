/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.Date;

/**
 *
 * @author jorge.correa
 */
public class ArchivoPlanoContrato {
    private String consecutivo;
    private String contratista;
    private String interventor;
    private String  tipoContrato;
    private String grupo;
    private String fechaInicio;
    private String fechaFin;
    private String duracion;
    private String valor;
    private String centroCostos;
    private String consecutivoLaborales;
    private String numeroCDP;
    private String estadoActual;
    private String fechaCreacion;
    private String objeto;
    private String correoContratista;

    public String getCorreoContratista() {
        return correoContratista;
    }

    public void setCorreoContratista(String correoContratista) {
        this.correoContratista = correoContratista;
    }
    
    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getContratista() {
        return contratista;
    }

    public void setContratista(String contratista) {
        this.contratista = contratista;
    }

    public String getInterventor() {
        return interventor;
    }

    public void setInterventor(String interventor) {
        this.interventor = interventor;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public String getConsecutivoLaborales() {
        return consecutivoLaborales;
    }

    public void setConsecutivoLaborales(String consecutivoLaborales) {
        this.consecutivoLaborales = consecutivoLaborales;
    }

    public String getNumeroCDP() {
        return numeroCDP;
    }

    public void setNumeroCDP(String numeroCDP) {
        this.numeroCDP = numeroCDP;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }    
    
}
