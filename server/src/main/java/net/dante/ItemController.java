package net.dante;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class ItemController<T> {
    private final Supplier<List<T>> getter;
    private final Consumer<T> adder;
    private final Function<T, T> mapper;

    protected ItemController(Supplier<List<T>> getter, Consumer<T> adder) {
        this(getter, adder, Function.identity());
    }

    protected ItemController(Supplier<List<T>> getter, Consumer<T> adder, Function<T, T> mapper) {
        this.getter = getter;
        this.adder = adder;
        this.mapper = mapper;
    }

    @GetMapping
    public List<T> getAll() {
        return getter.get();
    }

    @PostMapping
    public ResponseEntity<T> add(@RequestBody T item) {
        T mapped = mapper.apply(item);
        adder.accept(mapped);
        return ResponseEntity.status(201).body(mapped);
    }
}
