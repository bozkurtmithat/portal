package my.portal.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DtoList<T> extends DtoWrapper<List<T>> implements List<T> {

	private static final long serialVersionUID = 1L;
	private List<T> liste;

	public DtoList() {
		liste = new ArrayList<>();
	}

	public DtoList(int initialCapacity) throws IllegalArgumentException {
		liste = new ArrayList<>(initialCapacity);
	}

	public DtoList(List<T> a) {
		liste = new ArrayList<T>(a);
	}

	@Override
	public boolean add(T obj) {
		return liste.add(obj);
	}

	@Override
	public void add(int index, T object) throws ArrayIndexOutOfBoundsException {
		liste.add(index, object);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> col) {
		return liste.addAll(index, col);
	}

	@Override
	public boolean addAll(Collection<? extends T> col) {
		return liste.addAll(col);
	}

	@Override
	public boolean contains(Object object) {
		return liste.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> col) {
		return liste.containsAll(col);
	}

	@Override
	public void clear() {
		liste.clear();
	}

	@Override
	public T get(int index) {
		return liste.get(index);
	}

	public List<T> getList() {
		return liste;
	}

	@Override
	public List<T> getValue() {
		return liste;
	}

	@Override
	public int indexOf(Object obj) {
		return liste.indexOf(obj);
	}

	@Override
	public boolean isEmpty() {
		return liste.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return liste.iterator();
	}

	@Override
	public int lastIndexOf(Object obj) {
		return liste.lastIndexOf(obj);
	}

	@Override
	public ListIterator<T> listIterator() {
		return liste.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return liste.listIterator(index);
	}

	@Override
	public T remove(int index) {
		return liste.remove(index);
	}

	@Override
	public boolean remove(Object object) {
		return liste.remove(object);
	}

	@Override
	public boolean removeAll(Collection<?> col) {
		return liste.removeAll(col);
	}

	@Override
	public boolean retainAll(Collection<?> col) {
		return liste.retainAll(col);
	}

	@Override
	public T set(int index, T object) {
		return liste.set(index, object);
	}

	@Override
	public int size() {
		return liste.size();
	}

	@Override
	public List<T> subList(int start, int end) {
		return liste.subList(start, end);
	}

	@Override
	public Object[] toArray() {
		return liste.toArray();
	}

	@Override
	public <K> K[] toArray(K[] objects) {
		return liste.toArray(objects);
	}

	@Override
	public String toString() {
		return liste.toString();
	}
}
