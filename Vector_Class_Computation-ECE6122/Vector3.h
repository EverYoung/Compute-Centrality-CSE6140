#ifndef VECTOR3_H
#define VECTOR3_H

#include<iostream>
using namespace std;

struct Vector3
{
	float x;
	float y;
	float z;

	Vector3() = default;
	Vector3(float xyz);
	Vector3(float x, float y, float z);
	~Vector3() {};

	Vector3 operator+(const Vector3& rhs);
	Vector3 operator-(const Vector3& rhs);
	Vector3 operator*(const Vector3& rhs);
	Vector3 operator/(const Vector3& rhs);

	Vector3 operator+(float rhs);
	Vector3 operator-(float rhs);
	Vector3 operator*(float rhs);
	Vector3 operator/(float rhs);

	float operator|(const Vector3& rhs);
	Vector3 operator^(const Vector3& rhs);

	Vector3& operator+=(const Vector3& rhs);
	Vector3& operator-=(const Vector3& rhs);
	Vector3& operator*=(const Vector3& rhs);
	Vector3& operator/=(const Vector3& rhs);

	Vector3& operator++();
	Vector3 operator++(int __unused);
	Vector3& operator--();
	Vector3 operator--(int __unused);

	bool operator==(const Vector3& rhs);
	bool operator!=(const Vector3& rhs);

	void print();
};


#endif


