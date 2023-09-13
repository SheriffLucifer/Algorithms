/*Добавляем массив связных списков с фиксированным размером (массив бакетов), либо передаваемым в конструкторе.
Хэш-таблица оперирует индексами, потому массив будет идеальным вариантов для представления бакетов.
Также реализуем метод вычисления индекса на основании хэш-кода ключа.*/

public class Task2 {
    public class HashTable2<K, V> {
        private static final int INIT_BASKET_COUNT = 16;

        private Basket[] baskets;

        public HashTable2() {
            this(INIT_BASKET_COUNT);
        }

        public HashTable2(int initSize) {
            baskets = (Basket[]) new Object[initSize];
        }

        private int calculateBasketIndex(K key) {
            return key.hashCode() % baskets.length;
        }

        private class Entity {
            private K key;
            private V value;
        }

        private class Basket {
            private Node head;

            private class Node {
                private Node next;
                private Entity value;
            }
        }
    }
}