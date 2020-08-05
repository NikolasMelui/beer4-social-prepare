package beer4.social.controller;

import beer4.social.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 5;

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
        add(new HashMap<String, String>() {{ put("id", "4"); put("text", "Fourth message"); }});
    }};

    @GetMapping()
    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageById (@PathVariable String id) {
        return messages.stream().filter(message -> message.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public Map<String, String> createMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> currentMessage = getMessageById(id);
        currentMessage.putAll(message);
        currentMessage.put("id", id);
        return currentMessage;
    }

    @DeleteMapping("{id}")
    public int deleteMessage(@PathVariable String id) {
        Map<String, String> message = getMessageById(id);
        messages.remove(message);
        return 1;
    }
}
