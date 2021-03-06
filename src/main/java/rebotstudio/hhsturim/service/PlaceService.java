package rebotstudio.hhsturim.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.common.DateUtils;
import rebotstudio.hhsturim.entity.Place;
import rebotstudio.hhsturim.entity.PlaceType;
import rebotstudio.hhsturim.entity.Top;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.mapper.PlaceMapper;
import rebotstudio.hhsturim.repository.PlaceRepository;
import rebotstudio.hhsturim.repository.PlaceTypeRepository;
import rebotstudio.hhsturim.repository.TopRepository;
import rebotstudio.hhsturim.repository.UserRepository;
import rebotstudio.hhsturim.vo.CountVo;
import rebotstudio.hhsturim.vo.PlaceVo;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private PlaceTypeRepository placeTypeRepository;

    @Transactional
    public Page<Place> getAll(PageRequest pageRequest){        //获取所有的地点信息


//        List<Place> all = placeRepository.findAll();
////        List<PlaceVo> placeVos = PlaceMapper.toVoList(all);
////        for (PlaceVo placeVo : placeVos){
////            User user = userRepository.getOne(placeVo.getUid());
////            placeVo.setUsername(user.getUsername());
////        }
////
////        return placeVos;
        Page<Place> page = placeRepository.findAll(pageRequest);
        return page;

    }

    @Transactional
    public void savePlace(Place place){     //保存一个地点信息
        place.setCreateTime(new Date());
        placeRepository.save(place);
    }

    @Transactional
    public PlaceVo getOne(Integer id){        //根据id获取一个地点信息

        PlaceVo placeVo = PlaceMapper.toVo(placeRepository.getOne(id));
        placeVo.setUsername(userRepository.getOne(placeVo.getUid()).getUsername());
        return placeVo;
    }

    @Transactional
    public PlaceVo getById(Integer id){
        PlaceVo placeVo = PlaceMapper.toVo2(placeRepository.getOne(id));
        placeVo.setUsername(userRepository.getOne(placeVo.getUid()).getUsername());
        return placeVo;
    }

    public Page<Place> getPlaceByUserId(Integer uid, Pageable of){
        User user = userRepository.getOne(uid);
        Page<Place> byUid = placeRepository.findByUid(uid, of);
        return byUid;
//        List<PlaceVo> placeVos = PlaceMapper.toVoList(places);
//        placeVos.forEach(placeVo -> {placeVo.setUsername(user.getUsername());});
//        return placeVos;
    }

    @Transactional
    public List<Place> getTop(){            //获取top
        List<Top> all = topRepository.findAll();
        List<Place> list =new ArrayList<>();
        for(Top t:all){
            Place one = placeRepository.getOne(t.getPid());
            list.add(one);
        }
        return list;
    }

    @Transactional
    public void deleteTopById(Integer id){      //将id的地点从top中移除
        Top one = topRepository.getOne(id);
        topRepository.delete(one);
    }

    @Transactional
    public void addTop(Top top){        // 添加一个top
        topRepository.save(top);
    }

    @Transactional
    public List<PlaceVo> getBorder(){             //获取border
        List<PlaceVo> list=new ArrayList<>();
        List<Place> all = placeRepository.findAll();
        for(Place x:all){
            Optional<User> optional = userRepository.findById(x.getUid());

            PlaceVo placeVo = PlaceMapper.toVo(x);
            if(optional.isPresent()){
                placeVo.setUsername(optional.get().getUsername());
                placeVo.setPhone(optional.get().getPhone());
            }
            list.add(placeVo);
        }
       return list;
    }

    @Transactional
    public List<PlaceVo> getPlaceByType(Integer type){        //根据类型获取
        List<PlaceVo> list=new ArrayList<>();
        List<Place> placeByType = placeRepository.findPlaceByType(type);
        for(Place x:placeByType){
            User one = userRepository.getOne(x.getUid());
            PlaceVo placeVo = PlaceMapper.toVo(x);
            placeVo.setUsername(one.getUsername());
            placeVo.setPhone(one.getPhone());
            list.add(placeVo);
        }
        return list;
    }

    @Transactional
    public List<PlaceVo> getPlaceByPrice(Float start,Float end){        //根据价格区间查询

        List<PlaceVo> list=new ArrayList<>();
        List<Place> placeByPriceBetween = placeRepository.findPlaceByPriceBetween(start, end);
        for(Place x:placeByPriceBetween){
            User one = userRepository.getOne(x.getUid());
            PlaceVo placeVo = PlaceMapper.toVo(x);
            placeVo.setUsername(one.getUsername());
            placeVo.setPhone(one.getPhone());
            list.add(placeVo);
        }
        return list;
    }

    @Transactional
    public void deletePlaceById(Integer pid){       // 根据id删除一个场所信息
        Place place = placeRepository.getOne(pid);
        placeRepository.delete(place);
    }

    @Transactional
    public void updatePlaceById(Integer id,String title,String phone,String details,Integer type,Float price){  // 根据id修改地点信息
        Place place = placeRepository.getOne(id);
        place.setTitle(title);
        place.setPhone(phone);
        place.setDetails(details);
        place.setType(type);
        place.setPrice(price);
        placeRepository.save(place);
    }

    @Transactional
    public List<CountVo> getCount(Date date){               // 统计一个季度的发布情况

        List<CountVo> list  = new ArrayList<>();

        Date start = DateUtils.getCurrentQuarterStartTime(date);
        Date end = DateUtils.getCurrentQuarterEndTime(date);

        List<PlaceType> types = placeTypeRepository.findAll();
        for (PlaceType type: types){
            Integer count = placeRepository.countPlaceByTypeAndCreateTimeBetween(type.getId(), start, end);
            CountVo countVo=new CountVo();
            countVo.setTypeId(type.getId());
            countVo.setName(type.getTypeName());
            countVo.setValue(count);
            list.add(countVo);
        }
        return list;
    }

    @Transactional
    public List<PlaceVo> getAll(){  // 导出使用
        List<Place> places = placeRepository.findAll(Sort.by("createTime"));
        List<PlaceVo> placeVos = PlaceMapper.toVoList(places);
        for(PlaceVo placeVo: placeVos){
            Optional<User> byId = userRepository.findById(placeVo.getUid());
            placeVo.setUsername(byId.isPresent()?byId.get().getUsername():"");
        }
        return placeVos;
    }


}

