char p[] =
{
35,
105,
110,
99,
108,
117,
100,
101,
32,
60,
115,
116,
100,
105,
111,
46,
104,
62,
10,
10,
105,
110,
116,
32,
109,
97,
105,
110,
40,
118,
111,
105,
100,
41,
10,
123,
10,
105,
110,
116,
32,
105,
59,
10,
112,
114,
105,
110,
116,
102,
40,
34,
99,
104,
97,
114,
32,
112,
91,
93,
32,
61,
92,
110,
123,
92,
110,
34,
44,
32,
112,
44,
32,
112,
41,
59,
10,
102,
111,
114,
32,
40,
105,
61,
48,
59,
32,
112,
91,
105,
93,
59,
32,
105,
43,
43,
41,
10,
32,
32,
32,
32,
112,
114,
105,
110,
116,
102,
40,
34,
37,
100,
44,
92,
110,
34,
44,
32,
112,
91,
105,
93,
41,
59,
10,
112,
114,
105,
110,
116,
102,
40,
34,
48,
92,
110,
125,
59,
92,
110,
92,
110,
34,
41,
59,
10,
112,
114,
105,
110,
116,
102,
40,
34,
37,
115,
92,
110,
34,
44,
32,
112,
41,
59,
10,
114,
101,
116,
117,
114,
110,
32,
48,
59,
10,
125,
10,
0
};

#include <stdio.h>

int main(void)
{
int i;
printf("char p[] =\n{\n", p, p);
for (i=0; p[i]; i++)
    printf("%d,\n", p[i]);
printf("0\n};\n\n");
printf("%s\n", p);
return 0;
}

