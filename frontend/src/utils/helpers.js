export function paginate(paths) {
  const itemsPerPage = 5;
  const numberOfPages = Math.ceil(paths.length / itemsPerPage);

  const newPaths = Array.from({ length: numberOfPages }, (_, index) => {
    const start = index * itemsPerPage;
    return paths.slice(start, start + itemsPerPage);
  });
  return newPaths;
}
