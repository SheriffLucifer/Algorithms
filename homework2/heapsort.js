function heapSort(array) {
    heapify(array);
    end = array.length - 1;
    while (end > 0) {
        [array[end], array[0]] = [array[0], array[end]];
        end--;
        siftDown(array, 0, end);
    }
}

function heapify(array) {
    start = Math.floor(array.length / 2) - 1;

    while (start >= 0) {
        siftDown(array, start, array.length - 1);
        start--;
    }
}

function siftDown(array, startPosition, endPosition) {
    let rootPosition = startPosition;

    while (rootPosition * 2 + 1 <= endPosition) {
        childPosition = rootPosition * 2 + 1;
        if (childPosition + 1 <= endPosition && array[childPosition] < array[childPosition + 1]) {
            childPosition++;
        }
        if (array[rootPosition] < array[childPosition]) {
            [array[rootPosition], array[childPosition]] = [array[childPosition], array[rootPosition]];
            rootPosition = childPosition;
        } else {
            return
        }
    }
};

let arr = [12, 110, 150, 101, 19, 10, -52, 30, 13, 145, 4, -5, 68, 7, 88,];
console.log(arr);
heapSort(arr);
console.log(arr);
