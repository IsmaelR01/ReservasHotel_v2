package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Reservas {
    private ArrayList<Reserva> coleccionReservas = new ArrayList<>();

    public Reservas() {

    }

    public ArrayList<Reserva> get() {
        return copiaProfundaReservas();
    }

    private ArrayList<Reserva> copiaProfundaReservas() {
        ArrayList<Reserva> copiaReservas = new ArrayList<>();
        Iterator<Reserva> copiaReservaIterador = coleccionReservas.iterator();
        while(copiaReservaIterador.hasNext()) {
            copiaReservas.add(copiaReservaIterador.next());
        }
        return copiaReservas;
    }

    public int getTamano() {
        return coleccionReservas.size();
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        if(coleccionReservas.contains(reserva)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        coleccionReservas.add(reserva);
    }

    public Reserva buscar(Reserva reserva) {
        if(reserva == null) {
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        }
        if(coleccionReservas.contains(reserva)) {
            Iterator<Reserva> iteradorReserva = coleccionReservas.iterator();
            while(iteradorReserva.hasNext()) {
                if(reserva.equals(iteradorReserva.next())) {
                    return reserva;
                }
            }
        }
        return null;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        if (!coleccionReservas.contains(reserva)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        coleccionReservas.remove(reserva);
    }


    public ArrayList<Reserva> getReservas(Huesped huesped) {
        if(huesped == null) {
            throw  new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
        ArrayList<Reserva> listarReservasPorHuesped = new ArrayList<>();
        for(int i = 0; i< get().size(); i++) {
            if(coleccionReservas.get(i).getHuesped().equals(huesped)) {
                listarReservasPorHuesped.add(coleccionReservas.get(i));
            }
        }
        return listarReservasPorHuesped;
    }

    public ArrayList<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if(tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }

        ArrayList<Reserva> listarPorTipoHabitacion = new ArrayList<>();
        for(int i = 0; i< get().size(); i++) {
            if(coleccionReservas.get(i).getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                listarPorTipoHabitacion.add(coleccionReservas.get(i));
            }
        }
        return listarPorTipoHabitacion;
    }

    public ArrayList<Reserva> getReservasFuturas(Habitacion habitacion) {
        if(habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }

        ArrayList<Reserva> reservasFuturas = new ArrayList<>();
        for (int i = 0; i < get().size(); i++) {
            if (coleccionReservas.get(i).getHabitacion().equals(habitacion) &&
                    coleccionReservas.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturas.add(coleccionReservas.get(i));
            }
        }
        return reservasFuturas;
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha) {
        for (int i = 0; i < coleccionReservas.size(); i++) {
            Reserva reservaCheckIn = coleccionReservas.get(i);
            if (reservaCheckIn != null) {
                if (reservaCheckIn.equals(reserva)) {
                    reservaCheckIn.setCheckIn(fecha);
                }
            }
        }
    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha) {
        for (int i = 0; i < coleccionReservas.size(); i++) {
            Reserva reservaCheckOut = coleccionReservas.get(i);
            if (reservaCheckOut != null) {
                if (reservaCheckOut.equals(reserva)) {
                    reservaCheckOut.setCheckOut(fecha);
                }
            }
        }
    }

}


