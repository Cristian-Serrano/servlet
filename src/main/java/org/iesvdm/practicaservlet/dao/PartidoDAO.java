package org.iesvdm.practicaservlet.dao;

import org.iesvdm.practicaservlet.model.Partido;

import java.util.List;
import java.util.Optional;

public interface PartidoDAO {

    public void create(Partido partido);

    public List<Partido> getAll();
    public Optional<Partido> find(int id);

    public void update(Partido partido);

    public void delete(int id);
}
