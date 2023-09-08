package com.piseth.java.school.phoneshopenight.controller;
import com.piseth.java.school.phoneshopenight.Mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/models")//todo this relative path
public class ModelController {
    private  final ModelService modelService;

    private final ModelEntityMapper modelEntityMapper;
   // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    //todo have to provide request body
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
        Model model =  modelEntityMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelEntityMapper.toModelDTO(model));

    }
}
