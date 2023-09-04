package com.piseth.java.school.phoneshopenight.controller;
import com.piseth.java.school.phoneshopenight.Mapper.ModelMapper;
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

    private final ModelMapper modelMapper;
   // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    //todo have to provide request body
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
        Model model =  modelMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelMapper.toModelDTO(model));

    }
}
