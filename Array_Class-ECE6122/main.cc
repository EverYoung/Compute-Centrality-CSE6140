#include <iostream>
#include <vector>
#include "src/array.h"
#include "src/simple_string.h"
using namespace std;

//test your code here

int main() {

	simple_string a("fooa");
	simple_string b("foob");
	simple_string c("fooc");
	simple_string d("food");

	/*std::cout << "Vector" << std::endl;
	vector<simple_string> vec;
	vec.reserve(3);
	vec.push_back(a);
	vec.push_back(b);
	simple_string::initialize_counts();
	vec.insert(vec.begin(), c);
	simple_string::print_counts();*/

	std::cout << "Array" << std::endl;
	std::cout << "N copies constrcutor:" << std::endl;
	simple_string::initialize_counts();
	array<simple_string> arr(10);
	arr.push_back(c);
	arr.push_back(a);
	arr.push_back(a);
	arr.push_back(a);
	arr.push_back(b);
	simple_string::print_counts();
	std::cout << "Length:" << arr.length() << std::endl;
	std::cout << "Back:" << arr.back() << std::endl;
	std::cout << "Front:" << arr.front() << std::endl;
	std::cout << "Index3:" << arr[3] << std::endl;
	arr[3] = d;
	std::cout << "Index3 after change:" << arr[3] << std::endl;
	std::cout << "Clear" << std::endl;
	arr.clear();
	std::cout << "Empty?" << arr.empty() << std::endl;
	return 0;
}