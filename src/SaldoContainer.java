public class SaldoContainer {
        private double saldo;

        public SaldoContainer(double saldo) {
            this.saldo = saldo;
        }

        public double getSaldo() {
            return Math.round(saldo * 100.0) / 100.0;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
}
