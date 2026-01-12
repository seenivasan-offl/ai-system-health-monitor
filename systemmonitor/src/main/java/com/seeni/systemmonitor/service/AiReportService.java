package com.seeni.systemmonitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiReportService {

    private final WebClient webClient;
    private final String apiKey;

    public AiReportService(@Value("${groq.api-key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .build();
    }

    public String generateIncidentReport(float cpu, float ram, float disk) {

        String prompt = """
Write a short professional IT incident summary (max 2 lines).
Use the real values given. Return only plain text.

CPU Usage: %s%%
RAM Usage: %s%%
Disk Usage: %s%%
""".formatted(cpu, ram, disk);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.1-8b-instant");
        body.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));
        body.put("temperature", 0.3);
        body.put("max_tokens", 80);

        try {
            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .timeout(Duration.ofSeconds(10))        // ⏳ Prevent hanging
                    .retryWhen(Retry.max(2))                // 🔁 Retry twice if fails
                    .block();

            if (response == null || response.get("choices") == null) {
                return fallback(cpu, ram, disk);
            }

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) response.get("choices");

            if (choices.isEmpty()) {
                return fallback(cpu, ram, disk);
            }

            Map<String, Object> message =
                    (Map<String, Object>) choices.get(0).get("message");

            return message.get("content").toString().trim();

        } catch (Exception e) {
            System.out.println("AI API ERROR: " + e.getMessage());
            return fallback(cpu, ram, disk);
        }
    }

    // 🟢 Safe fallback summary
    private String fallback(float cpu, float ram, float disk) {
        return String.format(
                "High resource usage detected. CPU=%.1f%%, RAM=%.1f%%, Disk=%.1f%%. Immediate system check recommended.",
                cpu, ram, disk
        );
    }
}
