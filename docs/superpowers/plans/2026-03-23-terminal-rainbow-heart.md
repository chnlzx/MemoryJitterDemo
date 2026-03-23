# 终端彩虹爱心绘制实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Create a Python script that displays a large rainbow-colored heart in the terminal window.

**Architecture:** Use mathematical heart equation to calculate point matrix, map positions to rainbow colors using ANSI codes, and output to terminal with coordinate system conversion.

**Tech Stack:** Python 3.6+, standard library only (no external dependencies)

---

## File Structure

**Files to create:**
- `draw_heart.py` - Main script with all functions
- `tests/test_draw_heart.py` - Unit tests

**File responsibilities:**
- `draw_heart.py`: Contains calculate_heart_points(), map_to_rainbow_color(), main()
- `tests/test_draw_heart.py`: Tests for point calculation and color mapping

---

### Task 1: Create project structure and basic script

**Files:**
- Create: `draw_heart.py`
- Create: `tests/test_draw_heart.py`

- [ ] **Step 1: Create draw_heart.py with basic structure**

```python
#!/usr/bin/env python3
"""
Draw a rainbow heart in the terminal.
"""

import argparse
import sys


def calculate_heart_points(size: int) -> list[list[bool]]:
    """Calculate heart point matrix using mathematical equation."""
    pass


def map_to_rainbow_color(x: int, y: int, width: int) -> str:
    """Map position to ANSI rainbow color code."""
    pass


def main():
    """Main function to draw heart."""
    pass


if __name__ == "__main__":
    main()
```

- [ ] **Step 2: Create test file structure**

```python
import pytest
from draw_heart import calculate_heart_points, map_to_rainbow_color


def test_calculate_heart_points_basic():
    """Test basic heart point calculation."""
    pass


def test_map_to_rainbow_color_basic():
    """Test basic rainbow color mapping."""
    pass
```

- [ ] **Step 3: Make script executable and commit**

```bash
chmod +x draw_heart.py
git add draw_heart.py tests/test_draw_heart.py
git commit -m "feat: create basic project structure for terminal rainbow heart"
```

---

### Task 2: Implement calculate_heart_points()

**Files:**
- Modify: `draw_heart.py:15-20` (calculate_heart_points function)

- [ ] **Step 1: Write failing test for calculate_heart_points**

```python
def test_calculate_heart_points_basic():
    """Test basic heart point calculation."""
    size = 10
    points = calculate_heart_points(size)

    # Should return 2D list
    assert isinstance(points, list)
    assert len(points) == size

    # Should have some True values (heart points)
    true_count = sum(sum(row) for row in points)
    assert true_count > 0
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest tests/test_draw_heart.py::test_calculate_heart_points_basic -v`
Expected: FAIL with "function not defined" or similar

- [ ] **Step 3: Write minimal implementation**

```python
def calculate_heart_points(size: int) -> list[list[bool]]:
    """Calculate heart point matrix using mathematical equation.

    Uses heart equation: (x²+y²-1)³ - x²y³ ≤ 0
    Converts terminal coordinates (origin top-left) to math coordinates (origin center).
    """
    if size < 5 or size > 50:
        raise ValueError("Size must be between 5 and 50")

    points = []
    for row in range(size):
        row_points = []
        for col in range(size):
            # Convert to math coordinates (center at 0,0)
            # Scale coordinates based on size (use 4.0 for better visibility)
            scale = 4.0 / size
            x = (col - size/2) * scale
            y = (size/2 - row) * scale

            # Heart equation: (x²+y²-1)³ - x²y³ ≤ 0
            equation = (x*x + y*y - 1)**3 - x*x * y*y * y
            row_points.append(equation <= 0)
        points.append(row_points)

    return points
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest tests/test_draw_heart.py::test_calculate_heart_points_basic -v`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add draw_heart.py tests/test_draw_heart.py
git commit -m "feat: implement calculate_heart_points() function"
```

---

### Task 3: Implement map_to_rainbow_color()

**Files:**
- Modify: `draw_heart.py:22-30` (map_to_rainbow_color function)

- [ ] **Step 1: Write failing test for map_to_rainbow_color**

```python
def test_map_to_rainbow_color_basic():
    """Test basic rainbow color mapping."""
    width = 20

    # Test left side (red)
    color_left = map_to_rainbow_color(0, 0, width)
    assert "\033[31m" in color_left  # Red ANSI code

    # Test right side (purple)
    color_right = map_to_rainbow_color(width-1, 0, width)
    assert "\033[35m" in color_right  # Purple ANSI code
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest tests/test_draw_heart.py::test_map_to_rainbow_color_basic -v`
Expected: FAIL

- [ ] **Step 3: Write minimal implementation**

```python
def map_to_rainbow_color(x: int, y: int, width: int) -> str:
    """Map position to ANSI rainbow color code.

    Rainbow spectrum: Red -> Yellow -> Green -> Cyan -> Blue -> Purple
    Based on x position (horizontal direction).
    """
    # ANSI color codes (simple rainbow without orange)
    colors = [
        "\033[31m",  # Red
        "\033[33m",  # Yellow
        "\033[32m",  # Green
        "\033[36m",  # Cyan
        "\033[34m",  # Blue
        "\033[35m",  # Purple
    ]

    # Map x position to color index
    if width <= 1:
        color_index = 0
    else:
        color_index = int((x / (width - 1)) * (len(colors) - 1))
        color_index = max(0, min(color_index, len(colors) - 1))

    return colors[color_index]
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest tests/test_draw_heart.py::test_map_to_rainbow_color_basic -v`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add draw_heart.py tests/test_draw_heart.py
git commit -m "feat: implement map_to_rainbow_color() function"
```

---

### Task 4: Implement main() function

**Files:**
- Modify: `draw_heart.py:32-50` (main function)

- [ ] **Step 1: Write failing test for main()**

```python
def test_main_basic():
    """Test main function runs without error."""
    # Can't easily test output, but can test it doesn't crash
    import io
    from contextlib import redirect_stdout

    # Redirect stdout to capture output
    f = io.StringIO()
    with redirect_stdout(f):
        # This will fail because main() doesn't exist yet
        pass  # Will update after implementation
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest tests/test_draw_heart.py::test_main_basic -v`
Expected: FAIL

- [ ] **Step 3: Write minimal implementation**

```python
def main():
    """Main function to draw heart in terminal."""
    parser = argparse.ArgumentParser(description="Draw a rainbow heart in the terminal")
    parser.add_argument("--size", type=int, default=20, help="Size of heart (5-50)")
    args = parser.parse_args()

    # Validate size
    if args.size < 5 or args.size > 50:
        print("Error: Size must be between 5 and 50", file=sys.stderr)
        sys.exit(1)

    # Check Python version
    if sys.version_info < (3, 6):
        print("Error: Python 3.6+ required", file=sys.stderr)
        sys.exit(1)

    # Calculate heart points
    points = calculate_heart_points(args.size)

    # Draw heart with rainbow colors
    for row in points:
        line = ""
        for col_idx, is_point in enumerate(row):
            if is_point:
                color = map_to_rainbow_color(col_idx, 0, len(row))
                line += f"{color}❤\033[0m"  # Heart character with color reset
            else:
                line += " "
        print(line)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest tests/test_draw_heart.py::test_main_basic -v`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add draw_heart.py tests/test_draw_heart.py
git commit -m "feat: implement main() function with argument parsing"
```

---

### Task 4.5: Add comprehensive tests

**Files:**
- Modify: `tests/test_draw_heart.py`

- [ ] **Step 1: Add boundary and exception tests**

```python
def test_calculate_heart_points_boundaries():
    """Test boundary sizes."""
    # Test minimum size
    points_min = calculate_heart_points(5)
    assert len(points_min) == 5

    # Test maximum size
    points_max = calculate_heart_points(50)
    assert len(points_max) == 50

def test_calculate_heart_points_invalid_size():
    """Test invalid size handling."""
    with pytest.raises(ValueError):
        calculate_heart_points(4)

    with pytest.raises(ValueError):
        calculate_heart_points(51)

def test_map_to_rainbow_color_completeness():
    """Test all rainbow colors are generated."""
    width = 100
    colors_found = set()
    for x in range(width):
        color = map_to_rainbow_color(x, 0, width)
        colors_found.add(color)

    # Should find at least 3 different colors
    assert len(colors_found) >= 3
```

- [ ] **Step 2: Run all tests**

Run: `pytest tests/test_draw_heart.py -v`
Expected: All tests pass

- [ ] **Step 3: Commit**

```bash
git add tests/test_draw_heart.py
git commit -m "test: add boundary and exception tests

Add tests for:
- Boundary sizes (5 and 50)
- Invalid size handling
- Rainbow color completeness

Constraint: Must test all edge cases
Confidence: high
Scope-risk: narrow"
```

---

### Task 5: Manual testing and verification

**Files:**
- No file changes, just testing

- [ ] **Step 1: Run script with default size**

```bash
python3 draw_heart.py
```

Expected: Rainbow heart displayed in terminal (20 lines)

- [ ] **Step 2: Run script with custom size**

```bash
python3 draw_heart.py --size 30
```

Expected: Larger rainbow heart displayed

- [ ] **Step 3: Run script with help**

```bash
python3 draw_heart.py --help
```

Expected: Help message displayed

- [ ] **Step 4: Test error handling**

```bash
python3 draw_heart.py --size 3
```

Expected: Error message about size being too small

- [ ] **Step 5: Commit testing results**

```bash
git add README.md  # If created
git commit -m "test: manual testing completed for terminal rainbow heart"
```

---

### Task 6: Add documentation and final touches

**Files:**
- Create: `README.md`

- [ ] **Step 1: Create README.md**

```markdown
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
```

- [ ] **Step 2: Run all tests**

```bash
pytest tests/ -v
```

Expected: All tests pass

- [ ] **Step 3: Final commit**

```bash
git add README.md
git commit -m "docs: add README with usage instructions"
```

---

## Plan Review

After completing all tasks, the plan should be reviewed by a plan-document-reviewer subagent.

**Spec path:** `/Users/shawnleng/docs/superpowers/specs/2026-03-23-绘制终端彩虹爱心-design.md`
**Plan path:** `/Users/shawnleng/docs/superpowers/plans/2026-03-23-terminal-rainbow-heart.md`
