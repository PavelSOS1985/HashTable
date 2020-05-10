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
        byte[] arrBytesValue = value.getBytes();
        int sum = 0;
        for (byte i :
                arrBytesValue) {
            sum += i;
        }
        return sum % size;
    }

    // находит индекс пустого слота для значения, или -1
    public int seekSlot(String value) {
        int indexSlot = this.hashFun(value);
        int countPasses = 0;
        while (slots[indexSlot] != null) {
            if (slots[indexSlot].equals(value)) return indexSlot;
            indexSlot += step;
            if (indexSlot >= size && countPasses < step) {
                indexSlot = countPasses;
                countPasses++;
            }
            if (indexSlot >= size && countPasses >= step) {
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
        return this.seekSlot(value);
    }
}