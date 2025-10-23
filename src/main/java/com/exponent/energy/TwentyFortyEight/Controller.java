package com.exponent.energy.TwentyFortyEight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/2048")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

        @Autowired
        private Service service;
        private Model model;

        @PostMapping("/start")
        public Model startGame(@RequestParam(defaultValue = "4") int size) {
            model = new Model(size);
            return model;
        }

        @PostMapping("/move")
        public Model makeMove(@RequestParam String direction) {
            if (model == null) {
                model = new Model(4);
            }
            return service.move(model, direction);
        }

        @GetMapping("/state")
        public Model getModel() {
            if (model == null) {
                model = new Model(4);
            }
            return model;
        }

        @PostMapping("/restart")
        public Model restartGame(@RequestParam(defaultValue = "4") int size) {
            model = new Model(size);
            return model;
        }
}
