"use client";

import { auth, googleProvider } from "./config/firebase";
import { useState, useEffect } from "react";

export default function Home() {
    const [user, setUser] = useState(null);
  
    useEffect(() => {
      // Only run client-side code inside useEffect
      const checkUserStatus = () => {
        // Initialize or check Firebase auth status here if needed
      };
      checkUserStatus();
    }, []);
  
    const signInWithGoogle = async () => {
      try {
        const result = await auth.signInWithPopup(googleProvider);
        setUser(result.user);
      } catch (error) {
        console.error("Error signing in with Google: ", error);
      }
    };
  
    const signOut = async () => {
      try {
        await auth.signOut();
        setUser(null);
      } catch (error) {
        console.error("Error signing out: ", error);
      }
    };
  
    return (
      <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
        <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
          <Image
            className="dark:invert"
            src="/next.svg"
            alt="Next.js logo"
            width={180}
            height={38}
            priority
          />
  
          {user ? (
            <div>
              <p>Hello, {user.displayName}</p>
              <button onClick={signOut}>Sign Out</button>
            </div>
          ) : (
            <button onClick={signInWithGoogle}>Sign In with Google</button>
          )}
          
        </main>
      </div>
    );
  }