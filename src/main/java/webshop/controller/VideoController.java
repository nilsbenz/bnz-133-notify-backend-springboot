package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webshop.entities.Video;
import webshop.services.VideoService;

import java.util.List;

@RestController
public class VideoController {

    private VideoService videoService;

    @Autowired
    VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(value = "/api/videos", method = RequestMethod.GET)
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @RequestMapping(value = "/api/videos", method = RequestMethod.POST)
    public Long saveVideo(@RequestBody Video url) {
        Video video =  videoService.saveVideo(url);
        return video.getId();
    }
}
