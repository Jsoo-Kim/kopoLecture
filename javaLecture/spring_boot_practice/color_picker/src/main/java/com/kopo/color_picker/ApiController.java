package com.kopo.color_picker;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kopo.color_picker.dto.ColorRequestDTO;
import com.kopo.color_picker.dto.ResponseDTO;

@RestController
public class ApiController {
    // RGB 값을 처리하는 메소드
    @PostMapping("/select_action")
    public ResponseDTO handleColorSelection(@RequestBody ColorRequestDTO colorRequest) {
        int red = colorRequest.getRed();
        int green = colorRequest.getGreen();
        int blue = colorRequest.getBlue();

        return new ResponseDTO("Success", "Received RGB values: " + red + ", " + green + ", " + blue);
    }
}
