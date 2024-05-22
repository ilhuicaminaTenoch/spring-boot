package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicaciones;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicaciones publicacion = mapearEntidad(publicacionDTO);
        Publicaciones nuevaPublicacion = publicacionRepositorio.save(publicacion);

        return mapearDTO(nuevaPublicacion);
    }

    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        List<Publicaciones> publicaciones = publicacionRepositorio.findAll();
        return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(long id) {
        Publicaciones publicaciones = publicacionRepositorio.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearDTO(publicaciones);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicaciones publicacion = publicacionRepositorio.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        Publicaciones publicacionActualizada = publicacionRepositorio.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public PublicacionDTO eliminarPublicacion(long id) {
        Publicaciones publicacion = publicacionRepositorio.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepositorio.delete(publicacion);
        return null;
    }

    // convertir entidad a DTO
    private PublicacionDTO mapearDTO(Publicaciones publicacion) {
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setContenido(publicacion.getContenido());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        return publicacionDTO;
    }

    // convertir de DTO a Entidad
    private Publicaciones mapearEntidad(PublicacionDTO publicacionDTO) {
        Publicaciones publicacion = new Publicaciones();
        publicacion.setId(publicacionDTO.getId());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        return publicacion;
    }
}