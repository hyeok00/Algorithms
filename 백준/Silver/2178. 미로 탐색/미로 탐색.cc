#include<stdio.h>

int H, W;
int map[200][200];
int visit[200][200];

typedef struct Pos
{
	int x;
	int y;
}Pos;

Pos queueData[20000];
int front = -1, rear = -1;
void enQueue(Pos p)
{
	rear++;
	queueData[rear] = p;
}

Pos deQueue()
{
	front++;
	return queueData[front];
}

int isEmpty()
{
	return front == rear;
}


int main()
{
	scanf("%d %d", &H, &W);
	for (int i = 1; i <= H; i++)
	{
		for (int j = 1; j <= W; j++)
		{
			scanf("%1d", &map[i][j]);
		}
	}
	Pos start = { 1,1 };
	enQueue(start);
	visit[1][1] = 1;
	while (isEmpty() == 0)
	{
		Pos t = deQueue();
		Pos left = { t.x - 1,t.y };
		Pos right = { t.x + 1,t.y };
		Pos up = { t.x,t.y + 1 };
		Pos down = { t.x,t.y - 1 };
		if (map[left.y][left.x] == 1 && visit[left.y][left.x] == 0)
		{
			enQueue(left);
			visit[left.y][left.x] = visit[t.y][t.x] + 1;
		}
		if (map[right.y][right.x] == 1 && visit[right.y][right.x] == 0)
		{
			enQueue(right);
			visit[right.y][right.x] = visit[t.y][t.x] + 1;
		}
		if (map[up.y][up.x] == 1 && visit[up.y][up.x] == 0)
		{
			enQueue(up);
			visit[up.y][up.x] = visit[t.y][t.x] + 1;
		}
		if (map[down.y][down.x] == 1 && visit[down.y][down.x] == 0)
		{
			enQueue(down);
			visit[down.y][down.x] = visit[t.y][t.x] + 1;
		}
	}

	printf("%d", visit[H][W]);
	return 0;
}