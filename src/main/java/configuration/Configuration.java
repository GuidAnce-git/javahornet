package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    public RestAPI restAPI;
    public Dashboard dashboard;
    public Db db;
    public Snapshots snapshots;
    public Pruning pruning;
    public Protocol protocol;
    public Pow pow;
    public Requests requests;
    public Receipts receipts;
    public Tangle tangle;
    public Tipsel tipsel;
    public Node node;
    public P2p p2p;
    public Logger logger;
    public Warpsync warpsync;
    public Spammer spammer;
    public Mqtt mqtt;
    public Profiling profiling;
    public Prometheus prometheus;
    public Debug debug;

    public RestAPI getRestAPI() {
        return restAPI;
    }

    public void setRestAPI(final RestAPI restAPI) {
        this.restAPI = restAPI;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(final Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(final Db db) {
        this.db = db;
    }

    public Snapshots getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(final Snapshots snapshots) {
        this.snapshots = snapshots;
    }

    public Pruning getPruning() {
        return pruning;
    }

    public void setPruning(final Pruning pruning) {
        this.pruning = pruning;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(final Protocol protocol) {
        this.protocol = protocol;
    }

    public Pow getPow() {
        return pow;
    }

    public void setPow(final Pow pow) {
        this.pow = pow;
    }

    public Requests getRequests() {
        return requests;
    }

    public void setRequests(final Requests requests) {
        this.requests = requests;
    }

    public Receipts getReceipts() {
        return receipts;
    }

    public void setReceipts(final Receipts receipts) {
        this.receipts = receipts;
    }

    public Tangle getTangle() {
        return tangle;
    }

    public void setTangle(final Tangle tangle) {
        this.tangle = tangle;
    }

    public Tipsel getTipsel() {
        return tipsel;
    }

    public void setTipsel(final Tipsel tipsel) {
        this.tipsel = tipsel;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(final Node node) {
        this.node = node;
    }

    public P2p getP2p() {
        return p2p;
    }

    public void setP2p(final P2p p2p) {
        this.p2p = p2p;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(final Logger logger) {
        this.logger = logger;
    }

    public Warpsync getWarpsync() {
        return warpsync;
    }

    public void setWarpsync(final Warpsync warpsync) {
        this.warpsync = warpsync;
    }

    public Spammer getSpammer() {
        return spammer;
    }

    public void setSpammer(final Spammer spammer) {
        this.spammer = spammer;
    }

    public Mqtt getMqtt() {
        return mqtt;
    }

    public void setMqtt(final Mqtt mqtt) {
        this.mqtt = mqtt;
    }

    public Profiling getProfiling() {
        return profiling;
    }

    public void setProfiling(final Profiling profiling) {
        this.profiling = profiling;
    }

    public Prometheus getPrometheus() {
        return prometheus;
    }

    public void setPrometheus(final Prometheus prometheus) {
        this.prometheus = prometheus;
    }

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(final Debug debug) {
        this.debug = debug;
    }
}
