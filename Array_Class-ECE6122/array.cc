//
// Created by bpswe on 9/24/2018.
//

//implement your array code here

#include "array.h"
#include <cstdlib>
#include <iostream>
using namespace std;

template<typename T> array<T>::array() {
	m_elements = nullptr;
	m_size = 0;
	m_reserved_size = 1;
}

//initialize array with elements in initializer
template<typename T> array<T>::array(std::initializer_list<T> list) {
	m_elements = (T*)malloc(sizeof(T) * (list.size() + 1));
	array_iterator<T> iter(this->begin());
	for(size_t i = 0; i < list.size(); i++) {
		iter.m_current = new (iter.m_current) T(*(list.begin() + i));
		iter++;
	}
	m_size = list.size();
	m_reserved_size = list.size();
}

//copy constructor
template<typename T> array<T>::array(const array& rhs) {
	if (m_elements != nullptr) {
		array_iterator<T> iter(this->begin());
		while (m_size > 0) {
			iter.m_current->T::~T();
			iter++;
			m_size--;
		}
	}
	//Assign memeory
	m_elements = (T*)malloc(sizeof(T) * (rhs.m_reserved_size + 1));
	//Use iterator to copy object using placement new
	array_iterator<T> iter(this->begin());
	for (int i = 0; i < rhs.m_size; i++) {
		iter.m_current = new (iter.m_current) T(*(rhs.m_elements + i));
		iter++;
	}

	m_size = rhs.m_size;
	m_reserved_size = rhs.m_reserved_size;
}

//move constructor
template<typename T> array<T>::array(array&& rhs) {
	if (m_elements != nullptr) {
		array_iterator<T> iter(this->begin());
		while (m_size > 0) {
			iter.m_current->T::~T();
			iter++;
			m_size--;
		}
	}

	m_elements = rhs.m_elements;
	m_size = rhs.m_size;
	m_reserved_size = rhs.m_reserved_size;
	//Delete original one
	rhs.m_elements = nullptr;
	rhs.m_size = 0;
	rhs.m_reserved_size = 0;
}

//construct with initial "reserved" size
template<typename T> array<T>::array(size_t n) {
	m_elements = (T*)malloc(sizeof(T) * (n + 1));
	m_reserved_size = n;
	m_size = 0;
}

//construct with n copies of t
template<typename T> array<T>::array(size_t n, const T& t) {
	m_elements = (T*)malloc(sizeof(T) * (n + 1));
	m_reserved_size = n;
	m_size = 0;
	array_iterator<T> iter(this->begin());
	while (m_size < n) {
		iter.m_current = new (iter.m_current) T(t) ;
		iter++;
		m_size++;
	}
}

//destructor
template<typename T> array<T>::~array() {
	if (m_elements != nullptr) {
		array_iterator<T> iter(this->begin());
		while (m_size > 0) {
			iter.m_current->T::~T();
			iter++;
			m_size--;
		}
	}
	free(m_elements);
	m_reserved_size = 0;
}

//ensure enough memory for n elements
template<typename T> void array<T>::reserve(size_t n) {
	if (m_reserved_size > n) return;

	T* m_new_elements = (T*)malloc(sizeof(T) * (n + 1));
	array_iterator<T> tempOld(this->begin());
	array_iterator<T> tempNew(m_new_elements);
	while (tempOld != this->end()) {
		tempNew.m_current = new (tempNew.m_current) T(std::move(*(tempOld.m_current)));
		tempOld.m_current->T::~T();
		tempOld++;
		tempNew++;
	}
	free(m_elements);
	m_elements = m_new_elements;
	m_new_elements = nullptr;
	m_reserved_size = n;
}

//add to end of array
template<typename T> void array<T>::push_back(const T& obj) {
	size_t m_new_size = m_size + 1;
	if(m_reserved_size <= m_new_size) {
		m_reserved_size = m_reserved_size * 2;
		T* m_new_elements = (T*)malloc(sizeof(T) * (m_reserved_size + 1));
		array_iterator<T> tempOld(this->begin());
		array_iterator<T> tempNew(m_new_elements);
		while (tempOld != this->end()) {
			tempNew.m_current = new (tempNew.m_current) T(std::move(*(tempOld.m_current)));
			tempOld.m_current->T::~T();
			tempNew++;
			tempOld++;
		}
		tempNew.m_current = new (tempNew.m_current) T(obj);
		free(m_elements);
		m_elements = m_new_elements;
		m_new_elements = nullptr;
	}
	else {
		array_iterator<T> iter(this->end());
		iter.m_current = new (iter.m_current) T(obj);
	}
	m_size = m_new_size;
}

//add to front of array
template<typename T> void array<T>::push_front(const T& obj) {
	insert(this->begin(), obj);
}

//remove last element
template<typename T> void array<T>::pop_back() {
	array_iterator<T> iter(this->end());
	iter.m_current = iter.m_current - 1;
	iter.m_current->T::~T();
	m_size--;
}

//remove first element
template<typename T> void array<T>::pop_front() {
	/*array_iterator<T> iter(this->begin());
	for (; iter.m_current != (this->end().m_current - 1); iter++) {
		iter.m_current = new (iter.m_current) T(std::move(*(iter.m_current + 1)));
	}
	iter.m_current->T::~T();
	m_size--;
	*/
	erase(this->begin());
}

//return reference to first element
template<typename T> T& array<T>::front() const {
	return *m_elements;
}

//return reference to last element
template<typename T> T& array<T>::back() const {
	return *(m_elements + m_size - 1);
}

//return element to specified element
template<typename T> const T& array<T>::operator[](size_t index) const {
	array_iterator<T> iter(m_elements);
	iter.m_current += index;
	return *iter.m_current;
}

//return element to specified element
template<typename T> T& array<T>::operator[](size_t index) {
	array_iterator<T> iter(m_elements);
	iter.m_current += index;
	return *iter.m_current;
}

//return number of element
template<typename T> size_t array<T>::length() const {
	return m_size;
}

//return true if empty
template<typename T> bool array<T>::empty() const {
	return m_size == 0;
}

//remove all elements
template<typename T> void array<T>::clear() {
	if (m_elements != nullptr) {
		array_iterator<T> iter(this->begin());
		while (m_size > 0) {
			iter.m_current->T::~T();
			iter++;
			m_size--;
		}
	}
}

//obtain iterator to first element
template<typename T> array_iterator<T> array<T>::begin() const {
	return array_iterator<T>(m_elements);
}

//obtain iterator to last element
template<typename T> array_iterator<T> array<T>::end() const {
	return array_iterator<T>(m_elements + m_size); 
}

//remove specified element
template<typename T> void array<T>::erase(const array_iterator<T>& iter) {
	array_iterator<T> temp(iter);
	for (; temp.m_current != (this->end().m_current - 1); temp++) {
		*temp.m_current = std::move(*(temp.m_current + 1));
	}
	temp.m_current->T::~T();
	m_size--;
}

//insert element right before iter
template<typename T> void array<T>::insert(const array_iterator<T>& iter, const T& obj) {
	size_t m_new_size = m_size + 1;
	if (m_reserved_size > m_new_size) {
		cout << "k" << endl;
		T* tempObj = new T(obj);
		array_iterator<T> temp(this->end());
		for (; temp != iter; temp.m_current--) {
			if (temp == this->end()) {
				temp.m_current = new (temp.m_current) T(std::move(*(temp.m_current - 1)));
			}
			else *temp.m_current = std::move(*(temp.m_current - 1));
		}
		*temp.m_current = std::move(*tempObj);
		delete(tempObj);
	}
	else {
		m_reserved_size = m_reserved_size * 2;
		T* m_new_elements = (T*)malloc(sizeof(T) * (m_reserved_size + 1));
		array_iterator<T> tempOld(this->begin());
		array_iterator<T> tempNew(m_new_elements);
		while (tempOld != iter) {
			tempNew.m_current = new (tempNew.m_current) T(std::move(*tempOld.m_current));
			tempOld.m_current->T::~T();
			tempOld++;
			tempNew++;
		}
		tempNew.m_current = new (tempNew.m_current) T(obj);
		tempNew++;
		while (tempOld != this->end()) {
			tempNew.m_current = new (tempNew.m_current) T(std::move(*tempOld.m_current));
			tempOld.m_current->T::~T();
			tempOld++;
			tempNew++;
		}
		free(m_elements);
		m_elements = m_new_elements;
		m_new_elements = nullptr;
	}
	m_size = m_new_size;
}

//--------------------------------------------array_iterator-------------------------------------------------
template<typename T> array_iterator<T>::array_iterator() {
	m_current = nullptr;
}

template<typename T> array_iterator<T>::array_iterator(T* ptr) {
	m_current = ptr;
}

template<typename T> array_iterator<T>::array_iterator(const array_iterator& rhs) {
	m_current = rhs.m_current;
}

template<typename T> T& array_iterator<T>::operator*() const {
	return *m_current;
}

template<typename T> array_iterator<T> array_iterator<T>::operator++() {
	m_current++;
	return *this;
}

template<typename T> array_iterator<T> array_iterator<T>::operator++(int __unused) {
	array_iterator<T> temp(*this);
	m_current++;
	return temp;
}

template<typename T> bool array_iterator<T>::operator!=(const array_iterator<T>& rhs) const {
	return m_current != rhs.m_current;
}

template<typename T> bool array_iterator<T>::operator==(const array_iterator<T>& rhs) const {
	return m_current == rhs.m_current;
}