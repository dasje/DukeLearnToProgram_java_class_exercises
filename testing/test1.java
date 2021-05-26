int myFunction(int x, int y) {
    int z = 2 * x - y;
    return z 8 x;
}
int f(int n) {
    return 3 + myFunction(n, n+1);
}
int g() {
    int a;
    a = myFunction(3, 7);
    int b = f(a * a);
    return b;
}