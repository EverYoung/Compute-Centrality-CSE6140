#include "Vector3.h"

//Set components to xyz
Vector3::Vector3(float xyz) 
{
	x = xyz;
	y = xyz;
	z = xyz;
}

//Set components by names
Vector3::Vector3(float c1, float c2, float c3)
{
	x = c1;
	y = c2;
	z = c3;
}

//Component-wise add
Vector3 Vector3::operator+(const Vector3& rhs)
{
	Vector3 temp;
	temp.x = x + rhs.x;
	temp.y = y + rhs.y;
	temp.z = z + rhs.z;
	return temp;
}

//Component-wise substract
Vector3 Vector3::operator-(const Vector3& rhs)
{
	Vector3 temp;
	temp.x = x - rhs.x;
	temp.y = y - rhs.y;
	temp.z = z - rhs.z;
	return temp;
}

//Component-wise multiplication
Vector3 Vector3::operator*(const Vector3& rhs)
{
	Vector3 temp;
	temp.x = x * rhs.x;
	temp.y = y * rhs.y;
	temp.z = z * rhs.z;
	return temp;
}

//Component-wise division
Vector3 Vector3::operator/(const Vector3& rhs)
{
	Vector3 temp;
	temp.x = x / rhs.x;
	temp.y = y / rhs.y;
	temp.z = z / rhs.z;
	return temp;
}

//Add rhs to each component
Vector3 Vector3::operator+(float rhs)
{
	Vector3 temp;
	temp.x = x + rhs;
	temp.y = y + rhs;
	temp.z = z + rhs;
	return temp;
}

//Substract rhs from each component
Vector3 Vector3::operator-(float rhs)
{
	Vector3 temp;
	temp.x = x - rhs;
	temp.y = y - rhs;
	temp.z = z - rhs;
	return temp;
}

//Multiply rhs to each component
Vector3 Vector3::operator*(float rhs)
{
	Vector3 temp;
	temp.x = x * rhs;
	temp.y = y * rhs;
	temp.z = z * rhs;
	return temp;
}

//Divide each component by rhs
Vector3 Vector3::operator/(float rhs)
{
	Vector3 temp;
	temp.x = x / rhs;
	temp.y = y / rhs;
	temp.z = z / rhs;
	return temp;
}

//Dot product
float Vector3::operator|(const Vector3& rhs)
{
	return x * rhs.x + y * rhs.y + z * rhs.z;
}

//Cross product
Vector3 Vector3::operator^(const Vector3& rhs)
{
	Vector3 temp;
	temp.x = y * rhs.z - z * rhs.y;
	temp.y = z * rhs.x - x * rhs.z;
	temp.z = x * rhs.y - y * rhs.x;
	return temp;
}

//Component-wise add
Vector3& Vector3::operator+=(const Vector3& rhs)
{
	x += rhs.x;
	y += rhs.y;
	z += rhs.z;
	return *this;
}

//Component-wise substract
Vector3& Vector3::operator-=(const Vector3& rhs)
{
	x -= rhs.x;
	y -= rhs.y;
	z -= rhs.z;
	return *this;
}

//Component-wise multiplication
Vector3& Vector3::operator*=(const Vector3& rhs)
{
	x *= rhs.x;
	y *= rhs.y;
	z *= rhs.z;
	return *this;
}

//Component-wise division
Vector3& Vector3::operator/=(const Vector3& rhs)
{
	x /= rhs.x;
	y /= rhs.y;
	z /= rhs.z;
	return *this;
}

//Rotate Vector3 to the right
Vector3& Vector3::operator++()
{
	float temp = x;
	x = z;
	z = y;
	y = temp;
	return *this;
}

Vector3 Vector3::operator++(int __unused)
{
	Vector3 temp;
	float temp_value = x;
	temp.x = x;
	temp.y = y;
	temp.z = z;
	x = z;
	z = y;
	y = temp_value;
	return temp;
}

//Rotate Vector3 to the left
Vector3& Vector3::operator--()
{
	float temp = x;
	x = y;
	y = z;
	z = temp;
	return *this;
}

Vector3 Vector3::operator--(int __unused)
{
	Vector3 temp;
	float temp_value = x;
	temp.x = x;
	temp.y = y;
	temp.z = z;
	x = y;
	y = z;
	z = temp_value;
	return temp;
}

//Component-wise equality
bool Vector3::operator==(const Vector3& rhs)
{
	return x == rhs.x &&  y == rhs.y && z == rhs.z;
}

//Component-wise inequality
bool Vector3::operator!=(const Vector3& rhs) 
{
	return x != rhs.x || y != rhs.y || z != rhs.z;
}

//Print Vector3
void Vector3::print() {
	cout << x << " " << y << " " << z << endl;
}