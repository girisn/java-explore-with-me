package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.StatsHitDto;
import ru.practicum.dto.ViewStatsDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = "classpath:application.properties")
public class StatController {

    private final StatService service;

    @PostMapping("/hit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveStatsHit(@RequestBody @Valid StatsHitDto statsHitDto) {
        log.info("Save StatsHit {}", statsHitDto);
        service.saveStat(statsHitDto);
    }

    @GetMapping("/stats")
    public Collection<ViewStatsDto> getViewStats(
            @RequestParam(value = "start") LocalDateTime start,
            @RequestParam(value = "end") LocalDateTime end,
            @RequestParam(value = "uris", defaultValue = "") List<String> uris,
            @RequestParam(value = "unique", defaultValue = "false") Boolean unique
    ) {
        log.info("Get viewed stats with startDate {} endDate {}, uris {} unique {}", start, end, uris, unique);
        return service.getStats(start, end, uris, unique);
    }
}