public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    // всегда возвращает корректный индекс слота
    public int hashFun(String value) {
        byte[] arrBytesValue = value.getBytes();    // записываем байты элемента в массив
        int sum = 0;
        for (byte i :
                arrBytesValue) {
            sum += i;                               // считаем сумму байт
        }
        return sum % size;
    }

    // находит индекс пустого слота для значения, или -1
    public int seekSlot(String value) {
        int indexSlot = this.hashFun(value);                        // находим индекс элемента
        int countPasses = 0;                                        // вспомогательная переменная отвечающая за количество проходов по слотам
        while (slots[indexSlot] != null) {
            if (slots[indexSlot].equals(value))
                return indexSlot;                                   // если слот содержит такой же элемент то возвращать индекс этого слота
            indexSlot += step;                                      // изменение индекса слота на шаг
            if (indexSlot >= size && countPasses < step) {          // если индекс выходит за пределы таблицы и проходов меньше чем необходимо
                indexSlot = countPasses;                            // начинаем очередной проход сначала таблицы
                countPasses++;
            }
            if (indexSlot >= size && countPasses >= step) {         // если вышли за пределы и прошли все слоты возвращаем -1
                return -1;
            }
        }
        return indexSlot;
    }

    // записываем значение по хэш-функции
    public int put(String value) {
        int seekSlot = this.seekSlot(value);
        if (seekSlot == -1) return -1;
        slots[seekSlot] = value;
        return seekSlot;
    }

    // находит индекс слота со значением, или -1
    public int find(String value) {
        int i = this.seekSlot(value);
        return i == -1 ? -1 : slots[i] == null ? -1 : i;
    }
}