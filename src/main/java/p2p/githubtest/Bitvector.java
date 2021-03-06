package p2p.githubtest;

import com.google.common.base.Objects;
import org.apache.tuweni.bytes.Bytes;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static java.util.stream.Collectors.toList;


public class Bitvector {
    public static Bitvector fromBytes(Bytes bytes, int size) {
        checkArgument(
                bytes.size() == sszSerializationLength(size),
                "Incorrect data size (%s) for Bitvector of size %s",
                bytes.size(),
                size);
        BitSet bitset = new BitSet(size);

        for (int i = size - 1; i >= 0; i--) {
            if (((bytes.get(i / 8) >>> (i % 8)) & 0x01) == 1) {
                bitset.set(i);
            }
        }

        return new Bitvector(bitset, size);
    }

    public static int sszSerializationLength(final int size) {
        return (size + 7) / 8;
    }

    private final BitSet data;
    private final int size;

    private Bitvector(BitSet bitSet, int size) {
        data = bitSet;
        this.size = size;
    }

    public Bitvector(int size) {
        data = new BitSet(size);
        this.size = size;
    }

    public Bitvector(int size, Iterable<Integer> indicesToSet) {
        this(size);
        for (int i : indicesToSet) {
            data.set(i);
        }
    }

    public Bitvector(int size, int... indicesToSet) {
        this(size, Arrays.stream(indicesToSet).boxed().collect(toList()));
    }

    public List<Integer> getSetBitIndexes() {
        return data.stream().boxed().collect(toList());
    }

    public Bitvector withBit(int i) {
        checkElementIndex(i, size);
        BitSet newSet = (BitSet) data.clone();
        newSet.set(i);
        return new Bitvector(newSet, size);
    }

    public int getBitCount() {
        return data.cardinality();
    }

    public boolean getBit(int i) {
        checkElementIndex(i, size);
        return data.get(i);
    }

    public int getSize() {
        return size;
    }

    public IntStream streamAllSetBits() {
        return data.stream();
    }

    public Bytes serialize() {
        byte[] array = new byte[sszSerializationLength(size)];
        IntStream.range(0, size).forEach(i -> array[i / 8] |= ((data.get(i) ? 1 : 0) << (i % 8)));
        return Bytes.wrap(array);
    }

    public Bitvector rightShift(int i) {
        int length = getSize();
        BitSet newData = new BitSet(getSize());
        for (int j = 0; j < length - i; j++) {
            if (getBit(j)) {
                newData.set(j + i);
            }
        }
        return new Bitvector(newData, getSize());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bitvector)) {
            return false;
        }
        Bitvector bitvector = (Bitvector) o;
        return getSize() == bitvector.getSize() && Objects.equal(data, bitvector.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data, getSize());
    }

    @Override
    public String toString() {
        return "Bitvector{" + "data=" + data + ", size=" + size + '}';
    }
}
