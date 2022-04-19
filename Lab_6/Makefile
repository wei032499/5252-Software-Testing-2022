CXX := g++
FLAGS := -g -fno-stack-protector
TARGET := crossRedZone

asan: $(TARGET)
	$(CXX) $(FLAGS) $^.cpp -o $^ -fsanitize=address
	./$^

valgrind: $(TARGET)
	$(CXX) $(FLAGS) $^.cpp -o $^
	valgrind ./$^