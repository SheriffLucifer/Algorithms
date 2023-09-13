/*Добавляем информацию о размере хэш-таблицы, а также алгоритм увеличения
количества бакетов при достижении количества элементов до определенного размера относительно количества бакетов
(load factor).
Чтобы хэш-таблица сохраняла сложность поиска близкой к O(1), нам необходимо контролировать количество бакетов,
чтобы в них не скапливалось слишком много элементов, которые способны увеличить длительность операции поиска
и добавления.
В Java load factor для хэш-таблицы – 0.75, что значит, что при достижении количества значений 75%
от общего количества бакетов – это количество необходимо увеличить. Это позволяет минимизировать шансы,
что в бакетах будет больше 1-2 значений, а значит сохранит скорость поиска на уровне сложности O(1).
*/

public class Task6 {

    public class HashTable6<K, V> {
        private static final int INIT_BASKET_COUNT = 16;
        private Basket[] baskets;
        private static final double LOAD_FACTOR = 0.75;
        private int size = 0;

        public V get(K key) {
            int index = calculateBasketIndex(key);
            Basket basket = baskets[index];
            if (basket != null) {
                return basket.get(key);
            }
            return null;
        }

        public HashTable6() {
            this(INIT_BASKET_COUNT);
        }

        public HashTable6(int initSize) {
            baskets = (Basket[]) new Object[initSize];
        }

        private void recalculate() {
            Basket[] old = baskets;
            baskets = (Basket[]) new Object[old.length * 2];
            for (int i = 0; i < old.length; i++) {
                Basket basket = old[i];
                Basket.Node node = basket.head;
                while (node != null) {
                    put(node.value.key, node.value.value);
                    node = node.next;
                }
                old[i] = null;
            }
        }

        private int calculateBasketIndex(K key) {
            return key.hashCode() % baskets.length;
        }

        private class Entity {
            private K key;
            private V value;
        }

        public boolean put(K key, V value) {
            if (baskets.length * LOAD_FACTOR < size) {
                recalculate();
            }
            int index = calculateBasketIndex(key);
            Basket basket = baskets[index];
            if (basket == null) {
                basket = new Basket();
                baskets[index] = basket;
            }
            Entity entity = new Entity();
            entity.key = key;
            entity.value = value;
            boolean add = basket.add(entity);
            if (add) {
                size++;
            }
            return add;
        }

        public boolean remove(K key) {
            int index = calculateBasketIndex(key);
            Basket basket = baskets[index];
            boolean remove = basket.remove(key);
            if (remove) {
                size--;
            }
            return remove;
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

            public boolean remove(K key) {
                if (head != null) {
                    if (head.value.key.equals(key)) {
                        head = head.next;
                    } else {
                        Node node = head;
                        while (node.next != null) {
                            if (node.next.value.key.equals(key)) {
                                node.next = node.next.next;
                                return true;
                            }
                            node = node.next;
                        }
                    }
                }
                return false;
            }

            public boolean add(Entity entity) {
                Node node = new Node();
                node.value = entity;
                if (head != null) {
                    Node current = head;
                    while (true) {
                        if (current.value.key.equals(entity.key)) {
                            return false;
                        }
                        if (current.next == null) {
                            current.next = node;
                            return true;
                        } else {
                            current = current.next;
                        }
                    }
                } else {
                    head = node;
                    return true;
                }
            }

            private class Node {
                private Node next;
                private Entity value;
            }
        }
    }
}
