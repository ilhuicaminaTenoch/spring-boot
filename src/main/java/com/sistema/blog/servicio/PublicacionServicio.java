package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;

import java.util.List;

public interface PublicacionServicio {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacion);
    public List<PublicacionDTO> obtenerTodasLasPublicaciones();
    public PublicacionDTO obtenerPublicacionPorId(long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
    public PublicacionDTO eliminarPublicacion(long id);
}
