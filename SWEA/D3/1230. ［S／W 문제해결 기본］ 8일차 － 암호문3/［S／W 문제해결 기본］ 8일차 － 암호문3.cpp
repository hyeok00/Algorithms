#include<iostream>
using namespace std;

int T, N, temp;

struct Node
{
	int value;
	Node *next;
};

Node nodePool[5000];
int nodeCount;

void insert()
{
	int x,y;
	cin >> x;
	Node *node = &nodePool[0];
	for (int i = 0; i < x; ++i)
	{
		node = node->next;
	}
	cin >> y;
	for (int i = 0; i < y; ++i)
	{
		cin >> nodePool[++nodeCount].value;
		nodePool[nodeCount].next = node->next;
		node->next = &nodePool[nodeCount];
		node = node->next;
	}
	int abc = 1;
}

void remove()
{
	int x, y;
	cin >> x;
	Node *node = &nodePool[0];
	for (int i = 0; i < x; ++i)
	{
		node = node->next;
	}
	cin >> y;
	Node *modify = &nodePool[0];
	for (int i = 0; i < x + y + 1; ++i)
	{
		modify = modify->next;
	}
	*node->next = *modify;
}

void add()
{
	int x, y;
	cin >> x;
	for (int i = 0; i < x; ++i)
		cin >> y;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	T = 10;
	for (int tc = 1; tc <= T; ++tc)
	{
		nodeCount = 0;
		Node *head = &nodePool[0];
		head->value = -1;
		head->next = nullptr;
		Node *node = head;
		Node *nextNode;

		cin >> N;
		for (int i = 0; i < N; ++i)
		{
			cin >> temp;
			nextNode = &nodePool[++nodeCount];
			nextNode->value = temp;
			nextNode->next = nullptr;

			node->next = nextNode;
			node = nextNode;
		}

		cin >> N;
		for (int i = 0; i < N; ++i)
		{
			char c;
			cin >> c;
			switch (c)
			{
			case 'I':
				insert();
				break;
			case 'D':
				remove();
				break;
			default:
				add();
				break;
			}
		}

		head = nodePool[0].next;
		cout << "#" << tc << " ";
		for (int i = 0; i < 10; ++i) {
			cout << head->value << " ";
			head = head->next;
		}
		cout << "\n";
	}
	return 0;
}