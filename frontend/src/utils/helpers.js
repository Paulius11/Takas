// Function for paginate
export function paginate(paths) {
  // How many items are in the page
  const itemsPerPage = 5;
  const numberOfPages = Math.ceil(paths.length / itemsPerPage);

  // New products array, a function to cut paths array
  const newPaths = Array.from({ length: numberOfPages }, (_, index) => {
    const start = index * itemsPerPage;
    return paths.slice(start, start + itemsPerPage);
  });
  return newPaths;
}
