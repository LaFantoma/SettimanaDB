package com.generation.repositories;

import java.sql.Connection;

import com.generation.entities.Manifacturer;
import com.generation.entities.Poster;
import com.generation.library.List;

public class GigaRepository {

    private PosterRepository posterRepo;
    private ManifacturerRepository manifRepo;

    public GigaRepository(Connection con){

        this.posterRepo = new PosterRepository(con);
        this.manifRepo = new ManifacturerRepository(con);
    }

    public Poster readPosterbyID(int posterID) throws Exception{

        Poster res = posterRepo.readOne(posterID);
        Manifacturer m = manifRepo.readOne(res.id_manifacturer);
        res.myManifacturer = m;

        List<Poster> poster = new List<Poster>();
        poster.add(res);
        m.myPoster = poster;

        return res;
    }

    public Manifacturer readManifacturerById(int manifacturerId)throws Exception
    {
        Manifacturer res = manifRepo.readOne(manifacturerId);
        List<Poster> posters = posterRepo.madeBy(manifacturerId);

        res.myPoster = posters; 

        for(int i=0;i<posters.size();i++)
            posters.get(i).myManifacturer = res;

        return res;
    }
}


