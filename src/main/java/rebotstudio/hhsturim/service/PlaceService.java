package rebotstudio.hhsturim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.Place;
import rebotstudio.hhsturim.repository.PlaceRepository;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

   public List getAll(){        //获取所有的地点信息
       return placeRepository.findAll();
    }

    public Place getOne(Integer id){        //根据id获取一个地点信息
        return placeRepository.getOne(id);
    }

}
