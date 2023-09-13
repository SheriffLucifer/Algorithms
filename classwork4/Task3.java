/*
Реализуем метод поиска данных по ключу в хэш-таблице.
Теперь, когда у нас есть базовая структура нашей хэш-таблицы,
можно написать алгоритм поиска элементов, включающий в себя поиск нужного
бакета и поиск по бакету.
*/

public class Task3 {
    
    public class HashTable3<K, V> {
        private static final int INIT_BASKET_COUNT = 16;

        private Basket[] baskets;

        public V get(K key) {
            int index = calculateBasketIndex(key);
            Basket basket = baskets[index];
            if (basket != null) {
                return basket.get(key);
            }
            return null;
        }

        public HashTable3() {
            this(INIT_BASKET_COUNT);
        }

        public HashTable3(int initSize) {
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

            public V get(K key) {
                Node node = head;
                while (node != null) {
                    if (node.value.key.equals(key)) {
                        return node.value.value;
                    }
                    node = node.next;
                }
                return null;
            }

            private class Node {
                private Node next;
                private Entity value;
            }
        }
    }
}