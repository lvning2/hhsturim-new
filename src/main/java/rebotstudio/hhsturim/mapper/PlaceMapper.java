package rebotstudio.hhsturim.mapper;

import rebotstudio.hhsturim.entity.Place;
import rebotstudio.hhsturim.vo.PlaceVo;

public class PlaceMapper {

    public static PlaceVo toVo(Place place){
//        return CGlibMapper.mapper(place, PlaceVo.class);
        PlaceVo placeVo=new PlaceVo();
        placeVo.setId(place.getId());
        placeVo.setDetails(place.getDetails());
        placeVo.setImg(place.getImg());
        placeVo.setPhone(place.getPhone());
        placeVo.setTitle(place.getTitle());
        placeVo.setUid(place.getUid());
        placeVo.setPrice(place.getPrice());
        int t=place.getType();
        if(t==1){
            placeVo.setType("餐饮");
        }else if(t==2){
            placeVo.setType("商业街");
        }else if(t==3){
            placeVo.setType("娱乐");
        }else if(t==4){
            placeVo.setType("集会");
        }else if(t==5){
            placeVo.setType("竞赛");
        }else if(t==6){
            placeVo.setType("训练");
        }else if(t==7){
            placeVo.setType("其他");
        }

        return placeVo;

    }

}
