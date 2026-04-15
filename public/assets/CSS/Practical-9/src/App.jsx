import "./App.css";
import styles from "./Card.module.css";

function App() {
  const inlineCardStyle = {
    padding: "16px",
    borderRadius: "10px",
    border: "2px solid #2a7f62",
    backgroundColor: "#dff6ed",
    boxShadow: "0 8px 14px rgba(42, 127, 98, 0.12)"
  };

  return (
    <main className="page">
      <h1>Practical 9: CSS Approaches in React</h1>
      <p className="intro">
        This demo shows Inline CSS, External Stylesheet CSS, and CSS Modules in one React app.
      </p>

      <section className="demoGrid">
        <article style={inlineCardStyle}>
          <h3>1. Inline CSS</h3>
          <p>Style is defined directly in the component using a JavaScript object.</p>
        </article>

        <article className="stylesheetCard">
          <h3>2. External Stylesheet CSS</h3>
          <p>Style is defined in App.css and applied with a class name.</p>
        </article>

        <article className={styles.moduleCard}>
          <h3>3. CSS Module</h3>
          <p>Style is scoped locally through Card.module.css to avoid naming conflicts.</p>
        </article>
      </section>
    </main>
  );
}

export default App;
