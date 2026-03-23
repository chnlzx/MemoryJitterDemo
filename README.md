# Terminal Rainbow Heart

Draw a large rainbow-colored heart in the terminal using Python.

## Usage

```bash
# Basic usage (default 20 lines)
python3 draw_heart.py

# Custom size
python3 draw_heart.py --size 30

# View help
python3 draw_heart.py --help
```

## Requirements

- Python 3.6+
- Terminal with ANSI color support

## How it works

Uses mathematical heart equation `(x²+y²-1)³ - x²y³ ≤ 0` to calculate heart shape,
then maps positions to rainbow colors using ANSI escape codes.
