package br.com.vicente027.calc.model;
@FunctionalInterface
public interface MemoryObserver {
	// Todos os métodos dentro de interfaces são públicos, não sendo necessário implementar de forma explícita
	void changedValue(String newValue);
}
