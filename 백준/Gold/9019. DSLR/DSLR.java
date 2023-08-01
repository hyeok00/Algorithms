import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Register {
		int[] reg;
		StringBuilder keyBuilder;

		Register() {
			reg = new int[4];
			keyBuilder = new StringBuilder();
		}

		int regToNumber() {
			return reg[0] * 1000 + reg[1] * 100 + reg[2] * 10 + reg[3];
		}

		void numberToReg(int num) {
			reg[0] = num / 1000;
			reg[1] = num % 1000 / 100;
			reg[2] = num % 100 / 10;
			reg[3] = num % 10;
		}

		void funcD() {
			numberToReg((regToNumber() * 2) % 10000);
			keyBuilder.append("D");
		}

		void funcS() {
			numberToReg(((regToNumber() - 1) + 10000) % 10000);
			keyBuilder.append("S");
		}

		void funcL() {
			int temp = reg[0];
			reg[0] = reg[1];
			reg[1] = reg[2];
			reg[2] = reg[3];
			reg[3] = temp;
			keyBuilder.append("L");
		}

		void funcR() {
			int temp = reg[3];
			reg[3] = reg[2];
			reg[2] = reg[1];
			reg[1] = reg[0];
			reg[0] = temp;
			keyBuilder.append("R");
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			int start = Integer.parseInt(splitedLine[0]);
			int end = Integer.parseInt(splitedLine[1]);

			boolean[] visit = new boolean[10001];
			Queue<Register> q = new ArrayDeque<>();

			Register r = new Register();
			visit[start] = true;
			r.numberToReg(start);
			q.add(r);

			while (!q.isEmpty()) {
				Register cur = q.poll();
				if (cur.regToNumber() == end) {
					sb.append(cur.keyBuilder.toString()).append("\n");
					break;
				}
				Register D = funcD(cur);
				if (visit[D.regToNumber()] == false) {
					visit[D.regToNumber()] = true;
					q.add(D);
				}

				Register S = funcS(cur);
				if (visit[S.regToNumber()] == false) {
					visit[S.regToNumber()] = true;
					q.add(S);
				}

				Register L = funcL(cur);
				if (visit[L.regToNumber()] == false) {
					visit[L.regToNumber()] = true;
					q.add(L);
				}

				Register R = funcR(cur);
				if (visit[R.regToNumber()] == false) {
					visit[R.regToNumber()] = true;
					q.add(R);
				}

			}
		}
		System.out.println(sb);

	}

	private static Register funcD(Register cur) {
		Register r = new Register();
		r.numberToReg(cur.regToNumber());
		r.keyBuilder.append(cur.keyBuilder.toString());
		r.funcD();
		return r;
	}

	private static Register funcS(Register cur) {
		Register r = new Register();
		r.numberToReg(cur.regToNumber());
		r.keyBuilder.append(cur.keyBuilder.toString());
		r.funcS();
		return r;
	}

	private static Register funcL(Register cur) {
		Register r = new Register();
		r.numberToReg(cur.regToNumber());
		r.keyBuilder.append(cur.keyBuilder.toString());
		r.funcL();
		return r;
	}

	private static Register funcR(Register cur) {
		Register r = new Register();
		r.numberToReg(cur.regToNumber());
		r.keyBuilder.append(cur.keyBuilder.toString());
		r.funcR();
		return r;
	}

}