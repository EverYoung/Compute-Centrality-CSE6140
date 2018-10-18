#include <iostream>
#include "Vector3.h"
using namespace std;

int main() {
	Vector3 v1(17.5);
	Vector3 v2(2.5, 5, 7.5);
	Vector3 v3;
	float c = 8;

	cout << "v1 + v2 = " << endl;
	v3 = v1 + v2;
	v3.print();

	cout << "v1 - v2 = " << endl;
	v3 = v1 - v2;
	v3.print();

	cout << "v1 * v2 = " << endl;
	v3 = v1 * v2;
	v3.print();

	cout << "v1 / v2 = " << endl;
	v3 = v1 / v2;
	v3.print();

	cout << "v + c = " << endl;
	v3 = v2 + c;
	v3.print();

	cout << "v - c = " << endl;
	v3 = v2- c;
	v3.print();

	cout << "v * c = " << endl;
	v3 = v2 * c;
	v3.print();

	cout << "v / c = " << endl;
	v3 = v2 / c;
	v3.print();

	cout << "v1 ^ v2 = " << endl;
	v3 = v1 ^ v2;
	v3.print();

	c = v1 | v2;
	cout << "v1 | v2 = " << c << endl;

	cout << "v1 += v2, v1 =  " << endl;
	v1 += v2;
	v1.print();

	cout << "v1 -= v2, v1 =  " << endl;
	v1 -= v2;
	v1.print();

	cout << "v1 *= v2, v1 =  " << endl;
	v1 *= v2;
	v1.print();

	cout << "v1 /= v2, v1 =  " << endl;
	v1 /= v2;
	v1.print();

	(v2++).print();

	(++v2).print();

	(v2--).print();

	(--v2).print();

	cout << "v1 == v2 ? " << (v1 == v2) << endl;

	cout << "v1 != v2 ? " << (v1 != v2) << endl;

	system("pause");

	return 0;
}
