package com.example.demoot2.controller;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Tracer tracer;

    // Инжектируем Tracer через конструктор
    public TestController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/hello")
    public String hello() {
        // Создаем корневой спан (уже есть автоматически)
        Span methodSpan = tracer.spanBuilder("hello-method").startSpan(); // Новый спан
        try (Scope scope = methodSpan.makeCurrent()) {
            // Дополнительные спаны для внутренней логики
            Span calculationSpan = tracer.spanBuilder("calculate-length").startSpan();
            try {
                return "Hello, OpenTelemetry!";
            } finally {
                calculationSpan.end();
            }
        } finally {
            methodSpan.end();
        }
    }

    @GetMapping("/slow")
    public String slowEndpoint() throws InterruptedException {
        Thread.sleep(500); // Для проверки времени обработки
        return "Slow response";
    }
}
