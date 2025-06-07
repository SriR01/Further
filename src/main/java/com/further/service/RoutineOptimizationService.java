package com.further.service;

import com.further.model.RoutineDt;
import com.further.repository.RoutineDtRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutineOptimizationService {

    private final RoutineDtRepository routineDtRepository;

    public RoutineOptimizationService(RoutineDtRepository routineDtRepository) {
        this.routineDtRepository = routineDtRepository;
    }

    // Compute average time between consecutive goals for each routineId
    public Map<Long, Double> computeAverageGoalCompletionTimes(Long userId) {
        List<RoutineDt> routines = routineDtRepository.findByUserId(userId);
        Map<Long, List<Long>> routineDurations = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (RoutineDt routine : routines) {
            Long routineId = routine.getRoutineId();
            if (routineId == null) continue;
            // Only include routines that have been completed at least once
            if (routine.getWinFlag() == null || !routine.getWinFlag()) continue;
            String startTimeStr = routine.getStartTime();
            if (startTimeStr == null) continue;
            LocalTime prevTime;
            try {
                prevTime = LocalTime.parse(startTimeStr, formatter);
            } catch (Exception e) {
                continue;
            }

            // Collect flag times for this routine instance
            List<LocalTime> flagTimes = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Boolean flag = getGoalFlag(routine, i);
                Timestamp flagTimestamp = getGoalFlagTime(routine, i);

                if (!Boolean.TRUE.equals(flag) || flagTimestamp == null) continue;

                flagTimes.add(flagTimestamp.toLocalDateTime().toLocalTime());
            }

            // Only consider routines with at least 2 completed goals
            if (flagTimes.size() < 2) continue;

            // Calculate total time between consecutive goals (including start -> first goal)
            long totalDuration = 0;
            LocalTime lastTime = prevTime;
            for (LocalTime flagTime : flagTimes) {
                totalDuration += java.time.Duration.between(lastTime, flagTime).toMinutes();
                lastTime = flagTime;
            }

            routineDurations.computeIfAbsent(routineId, k -> new ArrayList<>()).add(totalDuration);
        }

        // Compute averages
        Map<Long, Double> averages = new HashMap<>();
        for (Map.Entry<Long, List<Long>> entry : routineDurations.entrySet()) {
            double avg = entry.getValue().stream().mapToLong(Long::longValue).average().orElse(Double.MAX_VALUE);
            averages.put(entry.getKey(), avg);
        }
        return averages;
    }

    // Sort todayRoutineIds by average completion time (fastest first)
    public List<Long> reorderRoutinesBySpeed(List<Long> todayRoutineIds, Long userId) {
        Map<Long, Double> averages = computeAverageGoalCompletionTimes(userId);
        return todayRoutineIds.stream()
                .sorted(Comparator.comparingDouble(id ->
                        averages.getOrDefault(id, Double.MAX_VALUE)
                ))
                .collect(Collectors.toList());
    }

    // Helper methods to access RoutineDt fields
    private Boolean getGoalFlag(RoutineDt routine, int i) {
        switch (i) {
            case 1: return routine.getGoal1Flag();
            case 2: return routine.getGoal2Flag();
            case 3: return routine.getGoal3Flag();
            case 4: return routine.getGoal4Flag();
            case 5: return routine.getGoal5Flag();
            default: return null;
        }
    }
    private Timestamp getGoalFlagTime(RoutineDt routine, int i) {
        switch (i) {
            case 1: return routine.getGoal1FlagTime();
            case 2: return routine.getGoal2FlagTime();
            case 3: return routine.getGoal3FlagTime();
            case 4: return routine.getGoal4FlagTime();
            case 5: return routine.getGoal5FlagTime();
            default: return null;
        }
    }
}
