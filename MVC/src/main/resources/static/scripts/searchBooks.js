document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');

    searchInput.addEventListener('input', function() {
        const title = searchInput.value;
        fetchBooks(title);
    });
});

const translations = {
    en: {
        bookTitle: "Title:",
        bookAuthor: "Author:",
        numberOfPages: "Number of pages:",
        genre: "Genre:",
        publishedDate: "Published date:",
        rented: "Rented:",
        archived: "Archived:",
        rentButton: "Rent",
        rentNowButton: "Rent Now",
        yes: "Yes",
        no: "No"
    },
    pl: {
        bookTitle: "Tytuł:",
        bookAuthor: "Autor:",
        numberOfPages: "Liczba stron:",
        genre: "Gatunek:",
        publishedDate: "Data publikacji:",
        rented: "Wypożyczona:",
        archived: "Zarchiwowana:",
        rentButton: "Wypożycz",
        rentNowButton: "Wypożycz Teraz",
        yes: "Tak",
        no: "Nie"
    }
};
function fetchBooks(title) {
    fetch(`books/search?title=${title}`)
        .then(response => response.json())
        .then(books => updateBookCards(books))
        .catch(error => console.error('Error:', error));
}

const userLang = (navigator.language || navigator.userLanguage).split('-')[0];
const lang = translations[userLang] ? userLang : 'en';

function updateBookCards(books) {
    const container = document.getElementById('bookCardsContainer');
    container.innerHTML = '';
    container.className= 'row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4';

    books.forEach(book => {
        const card = document.createElement('div');
        card.className = 'col';

        const rentedText = book.rented ? translations[lang].yes : translations[lang].no;
        const archivedText = book.archive ? translations[lang].yes : translations[lang].no;
        card.innerHTML = `
            <div class="card h-100">
                <div class="card-body">
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].bookTitle}</strong></div>
                        <div class="col-8">${book.title}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].bookAuthor}</strong></div>
                        <div class="col-8">${book.author}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].numberOfPages}</strong></div>
                        <div class="col-8">${book.numberOfPages}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].genre}</strong></div>
                        <div class="col-8">${book.genre}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].publishedDate}</strong></div>
                        <div class="col-8">${book.publishedDate}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].rented}</strong></div>
                        <div class="col-8">${rentedText}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>${translations[lang].archived}</strong></div>
                        <div class="col-8">${archivedText}</div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#rentModal" data-bs-book-id="${book.id}">${translations[lang].rentButton}</button>
                        </div>
                        <div class="col-6">
                            <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#rentNowModal" data-bs-book-id="${book.id}">${translations[lang].rentNowButton}</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        container.appendChild(card);
    });
}