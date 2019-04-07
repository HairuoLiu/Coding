# Latency Comparison Numbers

| Event                              | Nanoseconds   | Microseconds | Milliseconds | Comparison    |
|------------------------------------|--------------:|--------:|----:|-----------------------------|
| L1 cache reference                 |           0.5 |       - |   - | -                           |
| Branch mispredict                  |           5.0 |       - |   - | -                           |
| L2 cache reference                 |           7.0 |       - |   - | 14x L1 cache                |
| Mutex lock/unlock                  |          25.0 |       - |   - | -                           |
| Main memory reference              |         100.0 |       - |   - | 20x L2 cache, 200x L1 cache |
| Compress 1K bytes with Zippy       |       3,000.0 |       3 |   - | -                           |
| Send 1K bytes over 1 Gbps network  |      10,000.0 |      10 |   - | -                           |
| Read 4K randomly from SSD          |     150,000.0 |     150 |   - | ~1GB/sec SSD                |
| Read 1 MB sequentially from memory |     250,000.0 |     250 |   - | -                           |
| Round trip within same datacenter  |     500,000.0 |     500 |   - | -                           |
| Read 1 MB sequentially from SSD    |   1,000,000.0 |   1,000 |   1 | ~1GB/sec SSD, 4X memory     |
| Disk seek                          |  10,000,000.0 |  10,000 |  10 | 20x datacenter roundtrip    |
| Read 1 MB sequentially from disk   |  20,000,000.0 |  20,000 |  20 | 80x memory, 20X SSD         |
| Send packet CA → Netherlands → CA  | 150,000,000.0 | 150,000 | 150 | -                           |


## Unit Reference

* `1` ns = `10^-9` seconds
* `1` µs = `10^-6` seconds = `1,000` ns
* `1` ms = `10^-3` seconds = `1,000` µs = `1,000,000` ns


#Lets multiply all these durations by a billion:

Magnitudes:

### Minute:
    L1 cache reference                  0.5 s         One heart beat (0.5 s)
    Branch mispredict                   5 s           Yawn
    L2 cache reference                  7 s           Long yawn
    Mutex lock/unlock                   25 s          Making a coffee

### Hour:
    Main memory reference               100 s         Brushing your teeth
    Compress 1K bytes with Zippy        50 min        One episode of a TV show (including ad breaks)

### Day:
    Send 2K bytes over 1 Gbps network   5.5 hr        From lunch to end of work day

### Week
    SSD random read                     1.7 days      A normal weekend
    Read 1 MB sequentially from memory  2.9 days      A long weekend
    Round trip within same datacenter   5.8 days      A medium vacation
    Read 1 MB sequentially from SSD    11.6 days      Waiting for almost 2 weeks for a delivery

### Year
    Disk seek                           16.5 weeks    A semester in university
    Read 1 MB sequentially from disk    7.8 months    Almost producing a new human being
    The above 2 together                1 year

### Decade
    Send packet CA->Netherlands->CA     4.8 years     Average time it takes to complete a bachelor's degree

## Visual Comparison Charts

![Visual Comparison Chart](http://i.imgur.com/k0t1e.png)

![Scale of Computing Latencies](http://i.imgur.com/8LIwV4C.jpg)
